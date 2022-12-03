package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Repository
public class StudentRepository {
    HashMap<String,Student> studentMap=new HashMap<>();
    HashMap<String,Teacher> teacherMap=new HashMap<>();
    HashMap<String,List<String>> pairMap=new HashMap<>();
    ArrayList<String> list;
    public void addStudent(Student student){
        studentMap.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }
    public void addStudentTeacherPair(String student,String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            if(pairMap.containsKey(teacher)) list.add(student);
            else{
                list=new ArrayList<>();
                list.add(student);
                pairMap.put(teacher,list);
            }
        }
    }
    public Student getStudentByName(String student){
        return studentMap.get(student);
    }
    public Teacher getTeacherByName(String teacher){
        return teacherMap.get(teacher);
    }
    public List<String> getStudentsByTeacherName(String teacher){
        return pairMap.get(teacher);
    }
    public  List<String> getAllStudents(){
        List<String> studentList=new ArrayList<>();
        for(String student:studentMap.keySet()) studentList.add(student);
        return  studentList;
    }
    public void deleteTeacherByName(String teacher){
        if(pairMap.containsKey(teacher)){
            for(String s:pairMap.get(teacher)){
                studentMap.remove(s);
            }
            teacherMap.remove(teacher);
            pairMap.remove(teacher);
        }
    }
    public void deleteAllTeachers(){
        for(String s:pairMap.keySet()) deleteTeacherByName(s);
    }
}
