import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  isLoggedIn = localStorage.getItem('token')
  show: boolean = this.isLoggedIn ? true : false;
  whichUser: any;
}
