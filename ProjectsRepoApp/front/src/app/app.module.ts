import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { TokenInterceptorService } from './security-service/token-interceptor.service';
import { AuthenticationService } from './security-service/authentication.service';
import { CanActivateAuthGuard } from './security-service/can-activate-auth.guard';
import { JwtUtilsService } from './security-service/jwt-utils.service';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MainPageComponent } from './main-page/main-page.component';
import { ProjectPageComponent } from './project-page/project-page.component';
import { AddEditFormComponent } from './add-edit-form/add-edit-form.component';
import { CommentService } from './comment.service';
import { ProjectService } from './project.service';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PageNotFoundComponent,
    MainPageComponent,
    ProjectPageComponent,
    AddEditFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [ 
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    AuthenticationService,
    CanActivateAuthGuard,
    JwtUtilsService,
    CommentService,
    ProjectService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
