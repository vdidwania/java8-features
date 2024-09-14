package com.stream;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Employee> employees = employeeList();
		
		// Stream methods
		
		// Get all employee names as list
		
		//Without Stream
		List<String> empNames = new ArrayList<String>();
		for(Employee emp: employees) {
			empNames.add(emp.getName());
		}
		System.out.println("Emp names: "+empNames);
		
		//With Stream
		Stream<Employee> stream = employees.stream();
		empNames = stream.map(emp -> emp.getName()).collect(Collectors.toList());
		//stream.map(emp -> emp.getName()).toList();//modification to returned list not allowed and throws UnsupportedOperationException
		empNames.remove(5);
		//stream.map(emp -> empNames.add(emp.getName()));
		System.out.println(empNames);
		 
		//filter()
		stream = employees.stream();
		List<Employee> filteredEmployees = stream.filter(emp -> emp.getName().startsWith("T")).collect(Collectors.toList());
		System.out.println(filteredEmployees);
		
		stream = employees.stream();
		List<String> filteredEmpNames = stream.map(emp -> emp.getName()).filter(s -> s.startsWith("T")).collect(Collectors.toList());
		System.out.println(filteredEmpNames);
		
		//employee names whose age>25
		stream = employees.stream();
		List<String> filteredEmpNamesOnAge = stream.filter(emp -> emp.getAge()> 25)
				.map(emp -> emp.getName())
				.toList();
		System.out.println(filteredEmpNamesOnAge);

		//print all city names of employees
		//dintinct()
		//forEach()
		employees.stream().map(emp -> emp.getCity())
			.distinct()
			.forEach(s -> System.out.println(s));
		//method reference
		employees.stream().map(emp -> emp.getCity())
		.distinct()
		.forEach(System.out::println);
		
		//Get count of employees having salary>20000
		//count()
		Long count = employees.stream()
				.filter(emp -> emp.getSalary()>20000)
				.count();
		System.out.println(count);
		
		
		//Get first 3 employee objects as a list
		//limit()
		List<Employee> firstThreeEmployees = employees.stream().limit(3).toList();
		System.out.println(firstThreeEmployees);
		
		//Skip first 3 employees and get remaining employee objects as a list
		//skip()
		List<Employee> skikFirstThreeEmployees = employees.stream().skip(3).toList();
		System.out.println(skikFirstThreeEmployees);
		
		//Verify any emp age < 18
		//anyMatch()
		boolean isBelow18EmpFound = employees.stream().anyMatch(emp -> emp.getAge()<18);
		System.out.println(isBelow18EmpFound);
		
		//Verify every emp joined after 2010 or not
		//allMatch()
		
		boolean allEmpJoinedAfter = employees.stream().allMatch(emp -> emp.getYearOfJoining() > 2010);
		System.out.println(allEmpJoinedAfter);


		//noneMatch()
		//any one matching-> true
		//no one matching-> false
		boolean noEmpJoinedAfter = employees.stream().noneMatch(emp -> emp.getYearOfJoining() > 2018);
		System.out.println(noEmpJoinedAfter);
		boolean underPaid = employees.stream().noneMatch(emp -> emp.getSalary() < 10000);
		System.out.println(underPaid);
		
		//findAny()
		//Get one value out of all values, doesn't guarantee for first value
		Employee emp1 = employees.stream().findAny().get();
		System.out.println(emp1);
		
		//findAny()
		//first value will be returned always
		Employee emp2 = employees.stream().findFirst().get();
		System.out.println(emp2);
		
		//sorted()
		//Get employee IDs in sorted order
		List<Integer> sortedEmpIDList =  employees.stream()
				.map(emp -> emp.getId())
				.sorted()
				.toList();
		System.out.println(sortedEmpIDList);
		
		
		//sorted() with Comparator Arg
		//Define sorting based on Employee IDs and get the sorted list of Employee objects
		
		List<Employee> sortedEmployeeList = employees.stream().sorted((e1, e2) -> {
			return e1.getId() - e2.getId();
		}).toList();
		System.out.println(sortedEmployeeList);
		
		//Minimum salary employee details
		//min()
		Employee minSalaryEmp = employees.stream()
				.min((e1, e2) -> (int) (e1.getSalary()-e2.getSalary())).get();
		System.out.println(minSalaryEmp);

		
		//Maximum salary employee details
		//max()
		Employee maxSalaryEmp = employees.stream()
				.max((e1, e2) -> (int) (e1.getSalary()-e2.getSalary())).get();
		System.out.println(maxSalaryEmp);
		
		//average()
		//Average salary of all employees -> DoubleStream
		//mapToDouble
		double avgSalary = employees.stream()
				.mapToDouble(emp -> emp.getSalary())
				.average().getAsDouble();
		System.out.println(avgSalary);
		
		//Average age of all employees -> IntStream
		//mapToInt
		double avgAge = employees.stream()
				.mapToInt(emp -> emp.getAge())
				.average().getAsDouble();
		System.out.println(avgAge);
		
		//peek()
		//Out of all employees, find out list of employees whose year of joining > 2015
		//Track all employees getting processed
		System.out.println("******peek() method*******");

		List<Employee> filteredEmpWithPeek = employees.stream()
			.peek(emp -> System.out.println(emp))
			//.peek(System.out :: println)
			.filter(emp -> emp.getYearOfJoining() > 2015)
			//.peek(System.out :: println)
			.toList();
		System.out.println(filteredEmpWithPeek);
		
		
		System.out.println("******parallel stream*******");
		List<Employee> filteredEmpWithParallerStream =  employees.parallelStream()
			//.peek(emp -> System.out.println(emp))
			.peek(System.out :: println)
			.filter(emp -> emp.getYearOfJoining() > 2015)
			.toList();
		System.out.println(filteredEmpWithParallerStream);
		
		//Collectors class and methods
		System.out.println("******Collectors class and methods*******");
		//Collect all employees names whose age > 25
		List<String> filteredEmpListOnAge = employees.stream()
				.filter(emp -> emp.getAge()>25)
				.map(emp -> emp.getName())
				.collect(Collectors.toList());		
		System.out.println(filteredEmpListOnAge);
		
		//Unique department names
		List<String> uniqueDept = employees.stream()
				.map(Employee::getDepartment)
				.distinct()
				.collect(Collectors.toList());
		System.out.println(uniqueDept);
		//Unique department names through toSet()
		Set<String> uniqueDept2 = employees.stream()
				.map(Employee::getDepartment)
				.collect(Collectors.toSet());
		System.out.println(uniqueDept2);
		
		//Collect employee IDs and their salaries as map
		//toMap()
		Map<Integer, Double> empIdSalMap = employees.stream().collect(Collectors.toMap(emp -> emp.getId(), emp -> emp.getSalary()));
		Map<Integer, Double> empIdSalMap2 = employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getSalary));

		System.out.println(empIdSalMap);
		System.out.println(empIdSalMap2);

		//groupingBy()
		//avg salary by department wise
		Map<String, Double> avgSalDeptWise = employees.stream()
				.collect(Collectors
						.groupingBy(
								Employee::getDepartment, 
								Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(avgSalDeptWise);
		
		
		//groupingBy(), counting()
		//gender wise count of employees
		Map<String, Long> genderWiseEmpCount = employees.stream()
				.collect(Collectors
						.groupingBy(
								Employee::getGender, 
								Collectors.counting()));
		System.out.println(genderWiseEmpCount);
		
		
		//summing...Double, Long, Int
		//summing all employees salaries
		Double sumOfSalaries = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sumOfSalaries);
		
		//summing employees salaries by Gender
		Map<String, Double> sumOfSalariesGenderWise = employees.stream().collect(Collectors
				.groupingBy(Employee::getGender, 
						Collectors.summingDouble(Employee::getSalary)));
		System.out.println(sumOfSalariesGenderWise);
		
		//summarizing salary
		DoubleSummaryStatistics salarySummary =	employees.stream()
				.collect(Collectors
						.summarizingDouble(Employee::getSalary));
		System.out.println(salarySummary);
		//summarizing salary gender wise
		Map<String, DoubleSummaryStatistics> salarySummaryByGender =	employees.stream()
				.collect(Collectors
						.groupingBy(Employee::getGender,
						Collectors
						.summarizingDouble(Employee::getSalary)));
		System.out.println(salarySummaryByGender);
		
		//maxBy()
		Employee maxAgeEmployee = employees.stream().collect(Collectors.maxBy((em1, em2) -> em1.getAge() - em2.getAge())).get();
		System.out.println(maxAgeEmployee);
		
		
		//joining()
		String allDeptJoin = employees.stream()
				.map(Employee::getDepartment)
				.collect(Collectors.joining(","));
		System.out.println(allDeptJoin);
		
		
		//find department having longest string length
		Optional<String> longestDeptString = employees.stream().map(emp -> emp.getDepartment()).max((d1,d2) -> d1.length() - d2.length());
		longestDeptString.ifPresent(System.out::println);
		
		//reduce()
		Double sumOfAllSalary = employees.stream()
				.map(Employee::getSalary)
				.reduce(0.0, (sal1, sal2) -> sal1+sal2);
		System.out.println(sumOfAllSalary);

		
	}
	
	public static List<Employee> employeeList(){
		List<Employee> employeeList = new ArrayList<Employee>();
		
		employeeList.add(new Employee(6, "Six", 43, "Male", "Security", 2016, 9500.0, "Pune"));
		employeeList.add(new Employee(7, "Seven", 35, "Male", "Finance", 2010, 27000.0, "Pune"));
		employeeList.add(new Employee(3, "Three", 29, "Male", "Infrastructure", 2012, 18000.0, "Hyderabad"));
		employeeList.add(new Employee(8, "Eight", 31, "Male", "Development", 2015, 34500.0, "Pune"));
		employeeList.add(new Employee(9, "Nine", 24, "Female", "Sales", 2016, 11500.0, "Hyderabad"));
		employeeList.add(new Employee(10, "Ten", 25, "Female", "Sales", 2009, 22500.0, "Pune"));
		employeeList.add(new Employee(2, "Two", 25, "Male", "Sales", 2015, 13500.0, "Hyderabad"));
		employeeList.add(new Employee(4, "Four", 28, "Female", "Development", 2014, 32500.0, "Pune"));
		employeeList.add(new Employee(5, "Five", 27, "Female", "HR", 2013, 22700.0, "Pune"));
		employeeList.add(new Employee(1, "One", 32, "Female", "HR", 2011, 25000.0, "Hyderabad"));
		
		return employeeList;
	}

}
