import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  const token = localStorage.getItem('token');
  
  let clonedReq = req;
  if (token) {
    clonedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(clonedReq).pipe(
    catchError((error: HttpErrorResponse) => {
      // Si el token expiró o no está autorizado (401 / 403)
      if (error.status === 401 || error.status === 403) {
        localStorage.removeItem('token'); 
        router.navigate(['/login']);     
      }
      return throwError(() => error);
    })
  );
  }
  
  return next(req);
};
