package designpatterns;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Creature {
    private String name;
    private int attack;
    private int defense;
}

class CreatureModifier {
    protected Creature creature;
    private CreatureModifier next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    public void add(CreatureModifier cm) {
        if(next != null) {
            next.add(cm);
        } else {
            next = cm;
        }
    }

    public void handle() {
        if(next != null) {
            next.handle();
        }
    }
}


class DoubleAttackModifier extends CreatureModifier {

    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + creature.getName() + "'s attack");
        creature.setAttack(creature.getAttack() * 2);

        super.handle();
    }
}

class IncreaseDefenseModifier extends CreatureModifier {
    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Increasing " + creature.getName() + "'s defense");
        creature.setDefense(creature.getDefense() + 3);
        super.handle();
    }
}

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Creature creature = new Creature("Goblin", 2, 2);
        System.out.println(creature);
        CreatureModifier root = new CreatureModifier(creature);
        root.add(new DoubleAttackModifier(creature));
        root.add(new IncreaseDefenseModifier(creature));

        root.handle();

        System.out.println(creature);
    }
}
