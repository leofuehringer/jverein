/**********************************************************************
 * $Source$
 * $Revision$
 * $Date$
 * $Author$
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * This program is free software: you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
 *  the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, 
 * see <http://www.gnu.org/licenses/>.
 * 
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/

package de.jost_net.JVerein.gui.parts;

import java.rmi.RemoteException;
import java.util.List;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.io.SplitbuchungsContainer;
import de.jost_net.JVerein.rmi.Buchung;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.parts.TablePart;

public class SplitbuchungListTablePart extends TablePart
{

  public SplitbuchungListTablePart(Action action)
  {
    super(action);
  }

  public SplitbuchungListTablePart(GenericIterator list, Action action)
  {
    super(list, action);
  }

  public SplitbuchungListTablePart(List list, Action action)
  {
    super(list, action);
  }

  /**
   * Liefert den anzuzeigenden Summen-Text. Kann von abgeleiteten Klassen
   * ueberschrieben werde, um etwas anderes anzuzeigen.
   * 
   * @return anzuzeigender Text oder null, wenn nichts angezeigt werden soll.
   */
  @Override
  protected String getSummary()
  {
    String summary = super.getSummary();
    double sumBetrag = 0.0;
    try
    {
      List l = this.getItems();
      for (int i = 0; i < l.size(); i++)
      {
        Buchung b = (Buchung) l.get(i);
        sumBetrag += b.getBetrag();
      }
      // summary += " / Differenz:"
      // + " "
      // + Einstellungen.DECIMALFORMAT.format(SplitbuchungsContainer
      // .getDifference()) + " " + Einstellungen.CURRENCY;
      summary += " / Differenz:" + " " + SplitbuchungsContainer.getDifference()
          + " " + Einstellungen.CURRENCY;
    }
    catch (RemoteException re)
    {
      // nichts tun
    }
    return summary;
  }

}
