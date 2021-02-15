import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'home'
  },
  {
    path: 'home',
    loadChildren: () => import(`./home/home.module`)
      .then(m => m.HomeModule)
  },
  {
    path: 'demande',
    loadChildren: () => import(`./demande/demande.module`)
      .then(m => m.DemandeModule)
  },
  {
    path: 'admin',
    loadChildren: () => import(`./admin/admin.module`)
      .then(m => m.AdminModule)
  },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProtectedRoutingModule { }
