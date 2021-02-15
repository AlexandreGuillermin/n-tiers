import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MobilityApiService } from '../../shared/mobility-services/mobility-api.service';

@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.scss']
})
export class DemandeComponent implements OnInit {

  observable:Observable<Object>;

  constructor(private mobilityApiService: MobilityApiService, private router : Router) {}

  ngOnInit(): void {
  }

  /*Soumission du formulaire de demande à la BDD */
  onFormSubmit(userForm:NgForm){
    let userFormJson = JSON.stringify(userForm.value);
    this.observable=this.mobilityApiService.postMobility(userFormJson);
    let observer= this.observable.toPromise();
    observer
    .then(response => {
        console.log(response);
        this.router.navigateByUrl('/protected/admin');
    });
  }

}
