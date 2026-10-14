import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class loginService {


    private url = "http://localhost:8080/api/login"

    constructor(private http: HttpClient,private router: Router) { }

    login(data:any) {
        return this.http.post<any>(this.url,data);
    }
    logout() {
      localStorage.removeItem('token');
      this.router.navigate(['/productos']);
    }
}