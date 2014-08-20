/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package datduplicateremover.storage;

import java.io.File;
import java.util.LinkedList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class PlayersDataUtils {

	public static LinkedList<OfflinePlayer> getPlayers() {
		LinkedList<OfflinePlayer> players = new LinkedList<OfflinePlayer>();
		for (File file : getPlayersDataFolder().listFiles()) {
			if (file.getName().endsWith(".dat")) {
				String uuidstring = file.getName().substring(0, file.getName().length() - 4);
				try {
					players.add(Bukkit.getOfflinePlayer(UUID.fromString(uuidstring)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return players;
	}

	public static File getPlayerFile(OfflinePlayer player) {
		return new File(getPlayersDataFolder(), player.getUniqueId().toString()+".dat");
	}

	private static File getPlayersDataFolder() {
		return new File(Bukkit.getWorlds().get(0).getWorldFolder(), "playerdata");
	}

}
