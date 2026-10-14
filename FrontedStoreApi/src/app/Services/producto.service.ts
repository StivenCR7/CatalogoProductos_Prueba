import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private apiUrl = 'http://localhost:8080/api/productos'; 

  constructor(private http: HttpClient) { }

  listarProductos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  obtenerProductoPorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  actualizarProducto(id: number, producto: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/update/${id}`, producto);
  }

  //importar catálogo desde la API externa
  private url = "http://localhost:8080/api/catalogo";

  importarCatalogo(): Observable<any> {
        return this.http.post<any>(`${this.url}/import`, {});
    }

}