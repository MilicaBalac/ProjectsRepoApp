import { Component } from '@angular/core';
import { AuthenticationService } from './security-service/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'projekat';

  constructor(private autService: AuthenticationService) {

  }
  isLoggedIn(): boolean {
    return this.autService.isLoggedIn();
  }

  isLoggedOut(): boolean {
    return this.autService.isLoggedOut();
  }
  logOut() {
    this.autService.logout();
  }
}
