import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CartComponent } from './components/cart/cart.component';

const routes: Routes = [
  //định nghĩa các route

  {path: '',component:HomeComponent},
  {path: 'login',component:LoginComponent},
  {path: 'register',component:RegisterComponent},
  {path:'cart',component:CartComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes),HttpClientModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
