import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../service/auth/auth.service";
import {LoginLang} from "../model/lang";
import {AuthenticationException} from "../service/auth/auth-model";


@Component({selector: 'login', templateUrl: 'login.component.html', styleUrls: ['login.component.css']})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  returnUrl: string;
  hide: boolean = true;
  loginLang: LoginLang;

  isInvalidUserName: boolean;
  userErrorMessage: string;

  isInvalidPassword: boolean;
  passwordErrorMessage: string;


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {

  }

  ngOnInit() {
    this.loginLang = new LoginLang();

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    this.isInvalidUserName = false;
    this.isInvalidPassword = false;

    if (!this.loginForm.valid) {
      const control = this.loginForm.controls;

      if (control.username.errors && control.username.errors.required) {
        this.isInvalidUserName = true;
        this.userErrorMessage = this.loginLang.userNameRequired;
      }

      if (control.password.errors && control.password.errors.required) {
        this.isInvalidPassword = true;
        this.passwordErrorMessage = this.loginLang.passwordRequired;
      }
      return;
    }

    this.authService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(
      value => {
        this.loginForm.controls.username.setErrors(null);
        this.loginForm.controls.password.setErrors(null);

        console.log("Redirect to:", this.returnUrl);
        this.router.navigate([this.returnUrl]);
      }, error => {
        const authException = error as AuthenticationException;

        if (authException.code === AuthenticationException.USERNAME_NOT_FOUND.code) {
          this.isInvalidUserName = true;
          this.userErrorMessage = authException.message;
          this.loginForm.controls.username.setErrors(Validators.required);
          return;
        }

        if (authException.code === AuthenticationException.INCORRECT_USERNAME_OR_PASSWORD.code) {
          this.isInvalidPassword = true;
          this.passwordErrorMessage = authException.message;
          this.loginForm.controls.password.setErrors(Validators.required);
          return;
        }

        this.isInvalidUserName = true;
        this.isInvalidPassword = true;
        this.userErrorMessage = authException.message;
        this.passwordErrorMessage = authException.message;
        this.loginForm.controls.password.setErrors(Validators.required);

      });
  }

  s(event: any) {
    console.log(event)
    event.preventDefault()
  }
}
