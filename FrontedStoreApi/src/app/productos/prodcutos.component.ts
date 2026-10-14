import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../Services/producto.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-prodcutos',
  imports: [CommonModule, FormsModule],
  templateUrl: './prodcutos.component.html',
  styleUrl: './prodcutos.component.css'
})
export class ProdcutosComponent implements OnInit {
  productos: any[] = [];
  errorMessage: string = '';
  filtroTexto: string = '';
  productosFiltrados: any[] = [];
  successMessage: string = '';

  editarProducto: any = null;
  mostrarModal: boolean = false;
  
  constructor(private productService: ProductoService) {}

  ngOnInit(): void {
    this.ListarProductos();
  }

  ListarProductos(): void {
    this.productService.listarProductos().subscribe({
      next: (data) => {
        console.log('Datos recibidos del backend:', data);
        this.productos = data;
        this.productosFiltrados = data || [];
      },
      error: (error) => {
        this.errorMessage = 'Error al listar productos';
      }
    });
  }

  importarDatosApi(): void {
  this.errorMessage = '';
  this.successMessage = '';
  
  this.productService.importarCatalogo().subscribe({
    next: (data) => {
      this.successMessage = '¡Datos importados y sincronizados correctamente desde la API!';
      this.ListarProductos(); 
    },
    error: (err) => {
      this.errorMessage = 'Error al importar los datos de la API.';
      console.error(err);
    }
  });
}



  buscarProductos(): void {
    const termino = this.filtroTexto.toLowerCase().trim();
    if (!termino) {
      this.productosFiltrados = this.productos;
      return;
    }

    this.productosFiltrados = this.productos.filter(p => {
      const nombre = (p.nombre || '').toLowerCase();
      const categoria = (typeof p.categoria === 'object' ? p.categoria?.nombre : p.categoria || '').toLowerCase();
      return nombre.includes(termino) || categoria.includes(termino);
    });
  }
  abrirModalEdicion(p: any): void {
    this.editarProducto = { ...p };
    this.mostrarModal = true;
  }

  cerrarModal(): void {
    this.mostrarModal = false;
    this.editarProducto = null;
  }

  
  guardarEdicion(): void {
    if (!this.editarProducto) return;

    this.productService.actualizarProducto(this.editarProducto.id, this.editarProducto).subscribe({
      next: () => {
        this.successMessage = '¡Producto actualizado con éxito!';
        this.cerrarModal();
        this.ListarProductos();

        setTimeout(() => { this.successMessage = ''; }, 4000);
      },
      error: (err) => {
        this.errorMessage = 'Error al actualizar el producto.';
      }
    });
  }
}

