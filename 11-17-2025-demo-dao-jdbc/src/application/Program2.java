package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

//        System.out.println("=== TEST 1: Insert Department ===");
//        System.out.print("Enter the name of the department: ");
//        String name = sc.nextLine();
//        Department departments = new Department(null, name);
//        departmentDao.insert(departments);
//
//        System.out.println("Inserted Department with ID: " + departments.getId());

        System.out.println("\n=== TEST 2: FindById Department ===");
        Department department = departmentDao.findById(1);
        System.out.println("Name department: " + department.getName());

        System.out.println("\n=== TEST 3: Update Department ===");
        Department updatedDepartment = departmentDao.findById(6);
        updatedDepartment.setName("Cars");
        departmentDao.update(updatedDepartment);
        System.out.println("Updated department: " + updatedDepartment.getName());

        System.out.println("\n=== TEST 4: findAll Department ===");
        List<Department> allDepartments = departmentDao.findAll();

        for (Department dep : allDepartments) {
            System.out.println(dep);
        }

        System.out.println("\n=== TEST 5: Delete Department ===");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);


    }
}
