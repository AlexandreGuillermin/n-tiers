import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { MobilityApiService } from './mobility-services/mobility-api.service';
import { MobilityServices } from './mobility-services/mobility.service';


@NgModule({
  declarations: [NavbarComponent],
  imports: [
    CommonModule
  ],
  exports: [NavbarComponent]
})
export class SharedModule { 
}
