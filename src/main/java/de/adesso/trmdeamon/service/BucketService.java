package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.BucketDto;
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

    private final Mapper<Bucket, BucketDto> mapper = new Mapper<Bucket, BucketDto>() {
        @Override
        public BucketDto fromEntity(Bucket bucket) {

            return null;
        }

        @Override
        public Bucket fromDto(BucketDto dto) {

            return null;
        }
    };

    private final BucketsRepository repository;
    private final TimeSheetRepository timeSheetRepository;


    public BucketDto createBucket(BucketDto dto) {
        if(dto.getId() != null) throw new RuntimeException("ID was giveb");
        if(StringUtils.isEmpty(dto.getName())) throw new RuntimeException("No Name was given");
        if(dto.getTimeSheetId() == null) throw new RuntimeException("No Time-Sheet ID was given");
        TimeSheet ts = timeSheetRepository.findById(dto.getTimeSheetId()).orElseThrow(
                () -> new RuntimeException("Time-Sheet ID not found")
        );
        Bucket b = mapper.fromDto(dto);
        b.setTimeSheet(ts);
        if(dto.getParentBucketId() != null) {
            Bucket parent = repository.findById(dto.getParentBucketId()).orElseThrow(
                    () -> new RuntimeException("Bucketparent ID not found")
            );
            b.setParentBucket(parent);
        }
        return mapper.fromEntity(repository.save(b));
    }

    public BucketDto updateBucket(BucketDto dto) {
        if(dto.getId() != null) throw new RuntimeException("No ID was given");
        Bucket b = repository.findById(dto.getId()).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        if(StringUtils.isEmpty(dto.getName())) {
            b.setName(dto.getName());
        }
        if(dto.getParentBucketId() != null) {
            Bucket parent = repository.findById(dto.getParentBucketId()).orElseThrow(
                    () -> new RuntimeException("Bucketparent ID not found")
            );
            b.setParentBucket(parent);
        }
        return mapper.fromEntity(repository.save(b));
    }

    public void deleteBucket(Long id) {
        repository.deleteById(id);
    }

    public BucketDto getBucket(Long id) {
        Bucket b = repository.findById(id).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        return mapper.fromEntity(b);
    }

    public List<BucketDto> getAllBuckets() {
        return mapper.listFromEntity(repository.findAll());
    }
}
