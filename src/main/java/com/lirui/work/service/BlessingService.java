package com.lirui.work.service;

import com.lirui.work.entity.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlessingService {

    private static final String filePath = "D:\\employee_records.txt";

    public void sendEmail(List<Employee> employees) {
        employees.stream().filter(employee -> this.todayIsBirthday(employee.getBirthday()))
                .forEach(employee -> {
                    String email = employee.getEmail();
                    String emailText = "向邮箱：" + email + "发送生日祝福！";
                    System.out.println(emailText);
                });
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8"));

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",");
                employees.add(this.createEmployee(lineData));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("读取文件出错");
        }
        return employees;
    }

    private Employee createEmployee(String[] lineData) {
        Employee employee = new Employee();
        employee.setLastName(lineData[0]);
        employee.setFirstName(lineData[1]);
        employee.setBirthday(convDate(lineData[2]));
        employee.setEmail(lineData[3]);
        return employee;
    }

    private LocalDate convDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    private boolean todayIsBirthday(LocalDate birthday) {
        String birthdayStr = birthday.format(DateTimeFormatter.ofPattern("MMdd"));
        String nowDateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd"));
        return Objects.equals(birthdayStr, nowDateStr);
    }
}
