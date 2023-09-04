package TankImplementation;

import TankSimulation.BlocoCenario;
import TankSimulation.Tank;
import TankSimulation.TankArena;
import javaengine.CSprite;
import javaengine.CTempo;

public class EnergyTank extends Tank {

  int estado = 1;
  BlocoCenario ponto1;
  BlocoCenario ponto2;

  CTempo tempoPrint;

  public EnergyTank(CSprite[] sprite, TankArena arena) {
    super(sprite, arena);
    tempoPrint = new CTempo(1000);
    tempoPrint.inicia(gerenciadorTempo);

    ponto1 = matrizBlocos[9][9];
    ponto2 = matrizBlocos[5][7];
  }

  @Override
  public void executa() {
    this.rotacionaCanhao(180);
    tempoPrint.atualiza();
    if (!this.tankEmMovimento()) {
      if (estado == 1) {
        movePara(ponto2);
        estado = 2;
      } else {
        movePara(ponto1);
        estado = 1;
      }
    }
    if (tempoPrint.fimIntervalo()) {
      atirar();
      tempoPrint.reinicia();
      System.out.print(
        "Inimigo" +
        " " +
        retornaBlocoTankInimigo().linha +
        " " +
        retornaBlocoTankInimigo().coluna +
        " Total balas: " +
        rertornaTotalBalasTankInimigo()
      );
      if (temPowerUp()) {
        System.out.print(
          " Power Up " +
          retornaBlocoPowerUP().linha +
          " " +
          retornaBlocoPowerUP().coluna
        );
      }
      System.out.println();
    }
  }
}
