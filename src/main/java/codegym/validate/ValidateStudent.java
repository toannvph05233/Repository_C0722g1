package codegym.validate;


import codegym.model.Student;
import codegym.repository.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidateStudent implements Validator {
    @Autowired
    IStudentRepo iStudentRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
        Student student1 = iStudentRepo.findByName(student.getName());
        if (student1 != null){
            errors.rejectValue("name", "", "Trung name rồi");
            return;
        }
    }
}
