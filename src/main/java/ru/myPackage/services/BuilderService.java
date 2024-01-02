package ru.myPackage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myPackage.models.Builder;
import ru.myPackage.models.State;
import ru.myPackage.repositories.BuilderRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BuilderService {

    private final BuilderRepository builderRepository;

    @Autowired
    public BuilderService(BuilderRepository builderRepository) {
        this.builderRepository = builderRepository;
    }

    public List<Builder> findAll() {
        return builderRepository.findAll().stream().sorted().toList();
    }

    public Builder findOne(int id) {
        Optional<Builder> builder = builderRepository.findById(id);

        return builder.orElse(null);
    }

    public Builder findByEmail(String email) {
        return builderRepository.findBuilderByEmail(email);
    }

    @Transactional
    public void save(Builder builder) {
        String newIdOfEmployee = lustId();
        builder.setIdOfEmployee(newIdOfEmployee);
        builder.setState(State.NONE);
        builderRepository.save(builder);
    }

    @Transactional
    public void update(int id, Builder updateBuilder) {
        updateBuilder.setId(id);
        builderRepository.save(updateBuilder);
    }

    @Transactional
    public void updateTime(int id, int time) {

        Builder builderToBeUpdated = builderRepository.findById(id).get();

        int newTime = builderToBeUpdated.getTimeOfWork();
        builderToBeUpdated.setTimeOfWork(newTime + time);

        builderRepository.save(builderToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        builderRepository.deleteById(id);
    }

    public String lustId() {
        Builder builder = builderRepository.findAll().stream().sorted().max((b1, b2) -> b1.compareTo(b2)).orElse(null);
        if (builder != null) {
            String id = builder.getIdOfEmployee();
            String resInt = String.valueOf(Integer.parseInt(id.substring(1)) + 1);
            for (; ; ) {
                if (resInt.length() < 3) {
                    resInt = "0" + resInt;
                } else break;
            }
            resInt = id.charAt(0) + resInt;

            return resInt;
        } else {
            return "B001";
        }
    }
}
