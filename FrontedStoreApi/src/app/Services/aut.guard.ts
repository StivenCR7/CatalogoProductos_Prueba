// auth.guard.ts
import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const authGuard = () => {
  const router = inject(Router);
  const token = localStorage.getItem('token'); // O el método que uses para verificar sesión

  if (token) {
    return true;
  } else {
    alert('Por favor, inicie sesión para continuar.');
    router.navigate(['/login']);
    return false;
  }
};