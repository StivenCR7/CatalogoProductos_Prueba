import { HttpClient } from "@angular/common/http";
import { Inject } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs/internal/Observable";


@Inject({
    providedIn: 'root'
})
export class categoriasService {
    private url = "http://localhost:8080/api/categoria"

    constructor(private http: HttpClient) { }

    listarCategorias(): Observable<any> {
        return this.http.get<any>(this.url);
    }

}