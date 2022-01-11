package com.example.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.enums.SubjectNameFilter;
import com.example.response.StudentResponse;
import com.example.response.SubjectResponse;

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {

    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse, Collection<SubjectNameFilter> subjectNameFilter) {

        if (studentResponse.getStudent().getLearningSubjects() != null) {
            return studentResponse.getStudent().getLearningSubjects().stream().filter(i -> {
                if (subjectNameFilter.isEmpty()) {
                    return true;
                }
                return subjectNameFilter.stream().map(Enum::name).collect(Collectors.toList()).contains(i.getSubjectName());
            }).map(SubjectResponse::new)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    public String getFullName(StudentResponse studentResponse) {
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }
}
