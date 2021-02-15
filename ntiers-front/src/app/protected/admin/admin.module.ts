import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';

import {MobilityApiService} from '../../shared/mobility-services/mobility-api.service';
import { ListeMobilityComponent } from './liste-mobility/liste-mobility.component';
import { ModificationComponent } from './modification/modification.component'
import { FormsModule } from '@angular/forms';
import { RechercheComponent } from './recherche/recherche.component';


@NgModule({
  declarations: [AdminComponent, ListeMobilityComponent, ModificationComponent, RechercheComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [MobilityApiService],
})
export class AdminModule { }
