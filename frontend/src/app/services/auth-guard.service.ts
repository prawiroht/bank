import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {
  isLoggedIn = localStorage.getItem('token')
  constructor(private router:Router) { }
  canActivate(): boolean {
    if (this.isLoggedIn) {
      console.log('auth guard true');
      return true;
    } else {
      this.router.navigate(['login']);
      console.log('auth guard false');
      return false;
    }
  }
}
