import { Component, OnInit,Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { MobilityServices } from 'src/app/shared/mobility-services/mobility.service';
import { MobilityApiService } from '../../../shared/mobility-services/mobility-api.service';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.scss']
})
export class RechercheComponent implements OnInit {

  @Output() 
  modificationEvent = new EventEmitter<MobilityServices>();
  
  /*Variables API*/
  private observable:Observable<Object>;
  public mobilities:Array<MobilityServices>=new Array<MobilityServices>();

  /*Variables pour double binding des champs de recherche*/
  public ppnom : String = "";
  public nom : String = "";
  public ville : String = "";
  public pays: String = "";
  public promotion : String = "";
  public date_debut : Date = null;
  public date_fin : Date = null;  

  constructor(private mobilityService: MobilityApiService) {}

  /*Charge la liste depuis la BDD*/
  ngOnInit(){
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

  /*Charge la liste depuis la BDD à partir d'un filtre */
  onNgModelChange(){
    console.log("Updating...");
    this.observable=this.mobilityService.getMobilityFilter(this.createFilter());
    let observer= this.observable.toPromise();  
    observer
    .then(response => {
      console.log(response);
      //récupértion des clefs du tableau reçu
      let keys = Object.keys(JSON.parse(JSON.stringify(response)));
      //récupération des valeurs de chaques ligne du tableau
      let myjson : any;
      let mobility = new MobilityServices();
      //vider tableau des mobilités
      this.mobilities = new Array<MobilityServices>();
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

  /*Crée un filtre à transmettre au backend */
  createFilter() : MobilityServices {
    let mobility = new MobilityServices();
    mobility.ppnom=this.ppnom;
    mobility.nom=this.nom;
    mobility.ville=this.ville;
    mobility.pays=this.pays;
    mobility.promotion=this.promotion;
    mobility.date_debut=this.date_debut;
    mobility.date_fin=this.date_fin;
    return mobility;
  }

  /*Click sur la bouton bleu "modifier" */
  onClickModify(mobility: MobilityServices) {
    this.modificationEvent.emit(mobility);
  }

  /*Click sur le bouton rouge "supprimer" */
  onClickDelete(mobility: MobilityServices) {
    this.observable = this.mobilityService.delMobility(mobility);
    let observer = this.observable.toPromise();
    observer
      .then(response => {
        this.onNgModelChange();
      });
  }

}
