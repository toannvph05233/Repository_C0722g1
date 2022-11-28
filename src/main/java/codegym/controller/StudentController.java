package codegym.controller;

import codegym.model.ClassRoom;
import codegym.model.Student;
import codegym.repository.IClassRoomRepo;
import codegym.repository.IStudentRepo;
import codegym.validate.ValidateStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
public class StudentController {
    @Autowired
    IStudentRepo iStudentRepo;

    @Autowired
    IClassRoomRepo iClassRoomRepo;

    @Autowired
    ValidateStudent validateStudent;

    @GetMapping("students")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) throws Exception {
        ModelAndView modelAndView = new ModelAndView("show");
        Page<Student> students = iStudentRepo.findAll(PageRequest.of(page, 5, Sort.by("age")));
        modelAndView.addObject("students", students);
        throw new Exception("lỗi rồi Phong ơi");
    }

    @ModelAttribute(name = "classRooms")
    public List<ClassRoom> classRooms() {
        return (List<ClassRoom>) iClassRoomRepo.findAll();
    }

    @GetMapping("/create")
    public ModelAndView getCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model) {
        model.addAttribute("mess",e.getMessage());
       return "error";
    }


    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, @RequestParam long id_ClassRoom) {
        validateStudent.validate(student, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "create";
        }
        ClassRoom classRoom = iClassRoomRepo.findById(id_ClassRoom).get();
        student.setClassRoom(classRoom);
        iStudentRepo.save(student);
        return "redirect:/students";
    }
}
