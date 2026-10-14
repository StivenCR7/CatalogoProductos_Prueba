import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../Services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';
  
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  )
  {
    this.loginForm = this.fb.group({
      email: ['', Validators.required,],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
  console.log('Valores del form:', this.loginForm.value);
  console.log('¿Es válido el formulario?:', this.loginForm.valid);
  console.log('Errores en email:', this.loginForm.get('email')?.errors);
  console.log('Errores en password:', this.loginForm.get('password')?.errors);

  if (this.loginForm.valid) {
    this.authService.login(this.loginForm.value).subscribe({
      next: (res) => {
        console.log('Login exitoso:', res);
        this.router.navigate(['/productos']);
      },
      error: (err) => {
        this.errorMessage = 'Credenciales inválidas o error en el servidor';
        console.error('Error en el login:', err);
      }
    });
  } else {
    console.log('El formulario no es válido, revisa los errores arriba.');
  }
}
}
