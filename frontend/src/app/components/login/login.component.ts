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
    private messageService:MessageService,
    private app:AppComponent) { }

    dataAccess: any;
    dataMenu: any;
    penampungMenuId: any[] = [];
    
    logout(): void {
      console.log('inini')
      this.messageService.add({ key: 'tc', severity: 'info', summary: 'Goodbye', detail: 'Thank you, see you later' });
      localStorage.clear();
    }

  ngOnInit(): void {
    if (localStorage.getItem('token')) {
      this.router.navigate(['/login']);
    }
  }

  login() {
    this.userService.getByUsername(this.username).subscribe(
      res => {
        this.userData = res.data;
        console.log(this.userData, 'ini user data')
        if (res.status) {
          if (this.userData.password == this.password) {
            console.log(this.userData.permissions)
            for (let i in this.userData.permissions) {
              if (this.userData.permissions[i] == 'LOGIN') {
                this.successLogin()
                localStorage.setItem('token', this.userData.userId)
                localStorage.setItem('name', 'Administrator')
                for (let i in this.userData.permissions) {
                  if (this.userData.permissions[i] == 'VIEW') {
                    localStorage.setItem('isView', 'true');
                  }
                  if (res.data.permissions[i] == 'MANAGE') {
                    localStorage.setItem('isManage', 'true');
                  }
                }
                window.location.reload()
                return
              }
            }
            this.loginDenied();
          } else {
            this.wrongUser();
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
