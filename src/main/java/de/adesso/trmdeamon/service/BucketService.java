package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.buckets.BucketDto;
import de.adesso.trmdeamon.dto.buckets.ConstructBucketDto;
import de.adesso.trmdeamon.dto.buckets.UpdateBucketDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Bucket;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.BucketsRepository;
import de.adesso.trmdeamon.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketService {

    public static final Mapper<Bucket, ConstructBucketDto> constructionMapper = new Mapper<>() {
        @Override
        public Bucket fromDto(ConstructBucketDto dto) {
            return Bucket.builder()
                    .name(dto.getName())
                    .build();
        }
    };

    private final BucketsRepository repository;
    private final TimeSheetService timeSheetService;

    public TimeSheetDto createBucket(Long timeSheetId, ConstructBucketDto dto) {
        TimeSheet ts = timeSheetService.getTimeSheet(timeSheetId);
        Bucket parent = getBucket(dto.getParentBucketId());
        Bucket b = constructionMapper.fromDto(dto);
        b.setParentBucket(parent);
        b.setTimeSheet(ts);
        repository.save(b);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto updateBucket(Long timeSheetId, Long id, UpdateBucketDto dto) {
        Bucket b = getBucket(id);
        if(!StringUtils.isEmpty(dto.getName())) {
            b.setName(dto.getName());
        }
        if(dto.getParentBucketId() != null) {
            Bucket parent = getBucket(dto.getParentBucketId());
            if(b.getTimeSheet().getId().equals(parent.getTimeSheet().getId())) {
                b.setParentBucket(parent);
            } else {
                throw new RuntimeException("New Parent is not in same TimeSheet");
            }
        }
        repository.save(b);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    private Bucket getBucket(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Bucket not found")
        );
    }

    public TimeSheetDto deleteBucket(Long timeSheetId, Long id) {
        repository.deleteById(id);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

}
