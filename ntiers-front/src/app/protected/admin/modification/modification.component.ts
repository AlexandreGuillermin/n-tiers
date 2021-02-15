import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { MobilityServices } from 'src/app/shared/mobility-services/mobility.service';
import { MobilityApiService } from '../../../shared/mobility-services/mobility-api.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-modification',
  templateUrl: './modification.component.html',
  styleUrls: ['./modification.component.scss']
})
export class ModificationComponent implements OnInit {

  @Output()
  finModificationEvent = new EventEmitter<Boolean>();
  @Input()
  public mobilityToChange: MobilityServices;
  public originalMobility: MobilityServices;

  /*Variables API*/
  private observableDel: Observable<Object>;
  private observablePost: Observable<Object>;

  constructor(private mobilityService: MobilityApiService) { }

  ngOnInit(): void {
    this.originalMobility = new MobilityServices();
    this.originalMobility.set(this.mobilityToChange);
  }

  /*Envois les modification en base de données. */
  onFormSubmit(userForm: NgForm) {
    console.log(userForm);
    //création d'observer & observable pour appel d'API
    this.observableDel = this.mobilityService.delMobility(this.originalMobility);
    let observerDel = this.observableDel.toPromise();
    observerDel
      .then(response => {
        console.log(response);
        if (response == true) {
          let userFormJson = JSON.stringify(userForm.value);
          this.observablePost = this.mobilityService.postMobility(userFormJson);
          let observerPost = this.observablePost.toPromise();
          observerPost
            .then(response => {
              console.log(response);
              //Signale la fin de l'opération par un évenement (passage à la vue liste)
              this.finModificationEvent.emit(true);
            });
        }
      });
  }
}
