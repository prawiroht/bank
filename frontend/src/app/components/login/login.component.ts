import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AppComponent } from 'src/app/app.component';
import { UserService } from 'src/app/services/user.service';

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {
  
  username:string='';
  password:string='';
  checked: boolean = false;
  userData: any;
  userId: any;
  
  constructor(
    private userService:UserService,
    private router:Router,
    private messageService:MessageService) { }

    dataAccess: any;
    dataMenu: any;
    penampungMenuId: any[] = [];

  ngOnInit(): void {
    if (localStorage.getItem('token')) {
      this.router.navigate(['/home']);
    }
  }

  login() {
    this.userService.getByUsername(this.username).subscribe(
      res => {
        this.userData = res.data;
        if (res.status) {
          if (this.userData.username==this.username && this.userData.password == this.password) {
            this.successLogin()
            localStorage.setItem('token', this.userData.userId)
            window.location.reload();
            return
          }
        } else {
          this.wrongUser();
        }
      }
    );
  }


  wrongUser() {
    this.messageService.add({ key: 'tc', severity: 'warn', summary: 'Sorry', detail: 'Wrong username or password' });
  }

  successLogin() {
    this.messageService.add({ key: 'tc', severity: 'success', summary: 'Welcome', detail: 'Login success' });
  }

  loginDenied() {
    this.messageService.add({ key: 'tc', severity: 'warn', summary: 'Sorry', detail: 'You are not allowed to login' });
  }

}
