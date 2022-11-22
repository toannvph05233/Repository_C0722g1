package codegym.controller;

import codegym.model.ClassRoom;
import codegym.model.Student;
import codegym.repository.IClassRoomRepo;
import codegym.repository.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class StudentController {
    @Autowired
    IStudentRepo iStudentRepo;

    @Autowired
    IClassRoomRepo iClassRoomRepo;

    @GetMapping("students")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("show");
        List<Student> students = (List<Student>) iStudentRepo.findAll();
        modelAndView.addObject("students", students);
        return modelAndView;
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


    @PostMapping("/create")
    public String create(@ModelAttribute("student") Student student, @RequestParam long id_ClassRoom) {
        ClassRoom classRoom = iClassRoomRepo.findById(id_ClassRoom).get();
        student.setClassRoom(classRoom);
        iStudentRepo.save(student);
        return "redirect:/students";
    }
}
