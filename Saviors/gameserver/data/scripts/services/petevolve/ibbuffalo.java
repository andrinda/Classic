package services.petevolve;

import studio.lineage2.commons.dao.JdbcEntityState;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.xml.holder.NpcHolder;
import studio.lineage2.gameserver.data.xml.holder.PetDataHolder;
import studio.lineage2.gameserver.handler.bypass.Bypass;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.instances.PetInstance;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.Functions;

/**
 * User: darkevil
 * Date: 04.06.2008
 * Time: 1:06:26
 */
public class ibbuffalo
{
	private static final int BABY_BUFFALO = PetDataHolder.BABY_BUFFALO_ID;
	private static final int IMPROVED_BABY_BUFFALO = PetDataHolder.IMPROVED_BABY_BUFFALO_ID;
	private static final int BABY_BUFFALO_PANPIPE = 6648;
	private static final int IMPROVED_BABY_BUFFALO_NECKLACE = 10311;

	@Bypass("services.petevolve.ibbuffalo:evolve")
	public void evolve(Player player, NpcInstance npc, String[] param)
	{
		PetInstance pl_pet = player.getPet();
		if(player.getInventory().getItemByItemId(BABY_BUFFALO_PANPIPE) == null)
		{
			Functions.show("scripts/services/petevolve/no_item.htm", player, npc);
			return;
		}
		if(pl_pet == null || pl_pet.isDead())
		{
			Functions.show("scripts/services/petevolve/evolve_no.htm", player, npc);
			return;
		}
		if(pl_pet.getNpcId() != BABY_BUFFALO)
		{
			Functions.show("scripts/services/petevolve/no_pet.htm", player, npc);
			return;
		}
		if(Config.ALT_IMPROVED_PETS_LIMITED_USE && player.isMageClass())
		{
			Functions.show("scripts/services/petevolve/no_class_w.htm", player, npc);
			return;
		}
		if(pl_pet.getLevel() < 55)
		{
			Functions.show("scripts/services/petevolve/no_level.htm", player, npc);
			return;
		}

		int controlItemId = pl_pet.getControlItemObjId();
		pl_pet.unSummon(false);

		NpcTemplate template = NpcHolder.getInstance().getTemplate(IMPROVED_BABY_BUFFALO);
		if(template == null)
		{
			return;
		}

		ItemInstance control = player.getInventory().getItemByObjectId(controlItemId);
		control.setItemId(IMPROVED_BABY_BUFFALO_NECKLACE);
		control.setEnchantLevel(template.getLevel());
		control.setJdbcState(JdbcEntityState.UPDATED);
		control.update();

		player.sendItemList(false);

		Functions.show("scripts/services/petevolve/yes_pet.htm", player, npc);
	}
}