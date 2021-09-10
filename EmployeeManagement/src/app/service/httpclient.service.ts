import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";


@Injectable({
  providedIn: "root"
})
export class HttpClientService {
  constructor(private httpClient: HttpClient) {}

  getEmployees() {
    return this.httpClient.get<Employee[]>("http://localhost:8080/employees");
  }

  public deleteEmployee(employee) {
    return this.httpClient.delete<Employee>(
      "http://localhost:8080/employees" + "/" + employee.empId
    );
  }

  public createEmployee(employee) {
    return this.httpClient.post<Employee>(
      "http://localhost:8080/employees",
      employee
    );
  }
}

export class Employee {
  constructor(
    public empId: string,
    public name: string,
    public employeeType: string,
    public dob: string,
    public joiningDate: string,
    public designation: string,
    public salary: string,
    public terminationDate: string,
  ) {}
}