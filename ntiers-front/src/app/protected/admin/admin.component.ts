import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MobilityServices } from 'src/app/shared/mobility-services/mobility.service';
import { MobilityApiService } from '../../shared/mobility-services/mobility-api.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  
  /*Variables pour gérer l'affichage */
  public isModificationDisplay = false;
  public isListeMobilityDisplay = true;
  public isRechercheDisplay = false;

  /*Variable d'API */
  public mobilityToChange : MobilityServices;
  private observable:Observable<Object>;
  public mobilities:Array<MobilityServices>=new Array<MobilityServices>();

  constructor(private mobilityService: MobilityApiService) {}

  /*Chargement des mobilités depuis la BDD */
  ngOnInit(){
    this.mobilities=new Array<MobilityServices>();
    this.observable=this.mobilityService.getMobility();
    let observer= this.observable.toPromise();  
    observer
    .then(response => {
      console.log(response);
      //récupértion des clefs du tableau reçu
      let keys = Object.keys(JSON.parse(JSON.stringify(response)));
      //récupération des valeurs de chaques ligne du tableau
      let myjson : any;
      let mobility = new MobilityServices();
      keys.forEach(key => {
        myjson=response[key];
        mobility.ppnom=myjson["ppnom"];
        mobility.nom=myjson["nom"];
        mobility.ville=myjson["ville"];
        mobility.pays=myjson["pays"];
        mobility.promotion=myjson["promotion"];
        mobility.date_debut=myjson["date_debut"];
        mobility.date_fin=myjson["date_fin"];
        //ajout à la liste des mobilités
        this.mobilities.push(mobility);
        mobility= new MobilityServices();
      });
      console.log(this.mobilities);
    });
  }

  /*Gestion de l'évenement en cas de click sur modifier */
  modificationEventHander($event: any) {
    this.mobilityToChange= $event;
    this.isListeMobilityDisplay=false;
    this.isModificationDisplay = true;
    this.isRechercheDisplay = false;
  }

  /*Gestion de l'évenement une fois le formulaire de modification validé */
  finModificationEventHander($event: any) {
    if($event){
      this.isListeMobilityDisplay=true;
      this.isModificationDisplay = false;
      this.isRechercheDisplay = false;
      this.ngOnInit();
    }    
  }

  /*Gestion de l'évenement en cas de click sur le bouton "Rechercher" */
  rechercheEventHander($event: any) {
    this.isModificationDisplay = false;
    this.isListeMobilityDisplay = false;
    this.isRechercheDisplay = true;
  }
}

