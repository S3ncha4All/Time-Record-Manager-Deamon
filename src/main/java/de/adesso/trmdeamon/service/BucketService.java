package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.buckets.BucketDto;
import de.adesso.trmdeamon.dto.buckets.ConstructBucketDto;
import de.adesso.trmdeamon.dto.buckets.UpdateBucketDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Bucket;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.BucketsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BucketService {

    public static final Mapper<Bucket, ConstructBucketDto> ConstructionMapper = new Mapper<>() {
        @Override
        public Bucket fromDto(ConstructBucketDto dto) {
            return Bucket.builder()
                    .name(dto.getName())
                    .build();
        }
    };

    public static final Mapper<Bucket, BucketDto> BucketMapper = new Mapper<>() {
        @Override
        public BucketDto fromEntity(Bucket bucket) {
            return BucketDto.builder()
                    .id(bucket.getId())
                    .name(bucket.getName())
                    .childBuckets(listFromEntity(bucket.getChildren()))
                    .bookings(BookingService.BookingMapper.listFromEntity(bucket.getBookings()))
                    .build();
        }
    };

    private final BucketsRepository repository;
    private final TimeSheetService timeSheetService;

    public TimeSheetDto createBucket(Long timeSheetId, ConstructBucketDto dto) {
        TimeSheet ts = timeSheetService.getTimeSheet(timeSheetId);
        Bucket b = ConstructionMapper.fromDto(dto);
        if(dto.getParentBucketId() != null && dto.getParentBucketId() > 0) {
            Bucket parent = getBucket(timeSheetId, dto.getParentBucketId());
            b.setParentBucket(parent);
        }
        b.setTimeSheet(ts);
        repository.save(b);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto updateBucket(Long timeSheetId, Long id, UpdateBucketDto dto) {
        Bucket b = getBucket(timeSheetId, id);
        if(!StringUtils.isEmpty(dto.getName())) {
            b.setName(dto.getName());
        }
        if(dto.getParentBucketId() != null && dto.getParentBucketId() > 0) {
            Bucket parent = getBucket(timeSheetId, dto.getParentBucketId());
            b.setParentBucket(parent);
        }
        repository.save(b);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public void preCheck(Long timeSheetId, Bucket b) {
        TimeSheet ts = b.getTimeSheet();
        if(!timeSheetId.equals(ts.getId())) {
            throw new RuntimeException("Wrong TimeSheet-ID");
        }
    }

    public Bucket getBucket(Long timeSheetId, Long id) {
        Bucket b = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Bucket not found")
        );
        preCheck(timeSheetId, b);
        return b;
    }

    public TimeSheetDto deleteBucket(Long timeSheetId, Long id) {
        repository.deleteById(id);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

}
