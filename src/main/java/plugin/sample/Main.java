package plugin.sample;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.PublicKey;
import java.util.Collection;
import java.util.List;

public final class Main extends JavaPlugin implements Listener {

    private int count;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    /**
     * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
     *
     * @param e イベント
     */
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
        // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
        Player player = e.getPlayer();
        World world = player.getWorld();

        List<Color> colorlist;
        colorlist = List.of(Color.RED, Color.BLUE, Color.WHITE, Color.BLACK);
        if (count % 2 == 0) {
            for (Color color : colorlist) {
                Firework firework = world.spawn(player.getLocation(), Firework.class);
                FireworkMeta fireworkMeta = firework.getFireworkMeta();

                // メタ情報に対して設定を追加したり、値の上書きを行う。
                // 今回は青色で星型の花火を打ち上げる。
                fireworkMeta.addEffect(FireworkEffect.builder().withColor(color.RED).withColor(color.BLUE).with(Type.BALL_LARGE).withFlicker().build());
                int power = (8 / 4) + 1;
                fireworkMeta.setPower(fireworkMeta.getPower());

                // 追加した情報再設定する。
                firework.setFireworkMeta(fireworkMeta);
            }
            Path path = Path.of("firework.txt");
            Files.writeString(path, "たーまやー", StandardOpenOption.APPEND);
            player.sendMessage(Files.readString(path));
        }

        String message = null;
        System.out.println(message.length());

        count++;
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
        ItemStack[] itemStacks = player.getInventory().getContents();

        for (int i = 0; i <= itemStacks.length; i++) {
            ItemStack item = itemStacks[i];
            if (item != null && item.getAmount() < 64) {

                itemStacks[i] = null;
            }
        }

        player.getInventory().setContents(itemStacks);
    }

}



     public void




























