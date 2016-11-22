
package GameObject;

public class Plot Extends MapTile {
int resources[]=new int[3];
int ownership;
boolean enabled;
public Plot(int sres[]){
  resources[0]=sres[0];
  resources[1]=sres[1];
  resources[2]=sres[2];
  ownership=0;
  enabled=false;
}
public void interact(int player){
  if(player==ownership){
    if(enabled){
    upgradeMenu();
    }
    else{
    installMenu();
    }
  }
  else if(ownership==0){
  PurchaseMenu();
  }
}
}
