import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { MobilityServices } from 'src/app/shared/mobility-services/mobility.service';
import { MobilityApiService } from '../../../shared/mobility-services/mobility-api.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-liste-mobility',
  templateUrl: './liste-mobility.component.html',
  styleUrls: ['./liste-mobility.component.scss']
})
export class ListeMobilityComponent implements OnInit {

  @Input()
  mobilities: Array<MobilityServices>;
  @Output() 
  modificationEvent = new EventEmitter<MobilityServices>();
  @Output() 
  rechercheEvent = new EventEmitter<Boolean>();

  /*Variable API*/
  private observable: Observable<Object>;

  constructor(private mobilityService: MobilityApiService) { }

  ngOnInit(): void {
  }

  /*Bouton "Rechercher"*/
  onClickRecherche(){
    this.rechercheEvent.emit(true);
  }

  /*Bouton bleu pour modifier une demande */
  onClickModify(mobility: MobilityServices) {
    this.modificationEvent.emit(mobility);
  }

  /*Bouton rouge pour supprimer */
  onClickDelete(mobility: MobilityServices) {
    this.observable = this.mobilityService.delMobility(mobility);
    let observer = this.observable.toPromise();
    observer
      .then(response => {
        this.reload();
      });
  }

  /*Recharge la liste depuis la BDD*/
  reload() {
    this.mobilities = new Array<MobilityServices>();
    this.observable = this.mobilityService.getMobility();
    let observer = this.observable.toPromise();
    observer
      .then(response => {
        console.log(response);
        //récupértion des clefs du tableau reçu
        let keys = Object.keys(JSON.parse(JSON.stringify(response)));
        //récupération des valeurs de chaques ligne du tableau
        let myjson: any;
        let mobility = new MobilityServices();
        keys.forEach(key => {
          myjson = response[key];
          mobility.ppnom = myjson["ppnom"];
          mobility.nom = myjson["nom"];
          mobility.ville = myjson["ville"];
          mobility.pays = myjson["pays"];
          mobility.promotion = myjson["promotion"];
          mobility.date_debut = myjson["date_debut"];
          mobility.date_fin = myjson["date_fin"];
          //ajout à la liste des mobilités
          this.mobilities.push(mobility);
          mobility = new MobilityServices();
        });
        console.log(this.mobilities);
        this.ngOnInit();
      });
  }

}
