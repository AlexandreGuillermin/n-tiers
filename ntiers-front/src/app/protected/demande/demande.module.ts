import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DemandeRoutingModule } from './demande-routing.module';
import { DemandeComponent } from './demande.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MobilityApiService} from '../../shared/mobility-services/mobility-api.service'


@NgModule({
  declarations: [DemandeComponent],
  imports: [
    CommonModule,
    DemandeRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [MobilityApiService],
})
export class DemandeModule { }
