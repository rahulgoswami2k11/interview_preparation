package interview;

/*
Entities that are alive have **life points** ❤️.

Some living entities have the ability to inflict damage on other living entities by biting them with their **bite force**. This can reduce the victim's **life points**. ⚔️
- Let's consider a living entity called **Barracuda** 🐟 with **6 ❤️ life points**.
- A **Shark** 🦈 is a living entity with **14** ❤️ **life points**.
- and a **Human** 🙆‍♂️, a living entity with **10 ❤️ life points**.
- **Barracuda** 🐟 can bite 😬 another living entity with **bite force 4** ⚔️
- **Shark** 🦈 can bite 😬 another living entity with **bite force 7** ⚔️
- **Humans** 🙆‍♂️ cannot bite
Test Cases -
- **Barracuda** 🐟 bite **Human** 🙆‍♂️
- **Shark** 🦈 bite **Human** 🙆‍♂️
- **Human** 🙆‍♂️ dies 💀 🪦
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Entity {
    private int lifePoints;

    public Entity(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void reduceLifePoints(int damage) {
        this.lifePoints = Math.max(0, this.lifePoints - damage);
    }

    public boolean isAlive() {
        return this.lifePoints > 0;
    }
}

interface Biteable {
    int getBiteForce();

    default void bite(Entity entity) {
        if(entity instanceof NonBiteable) {
            entity.reduceLifePoints(getBiteForce());
        }
    }
}

interface NonBiteable {}

@Getter
@Setter
class Shark extends Entity implements Biteable {
    private int biteForce;
    public Shark(int lifePoints, int biteForce) {
        super(lifePoints);
        this.biteForce = biteForce;
    }

    @Override
    public int getBiteForce() {
        return biteForce;
    }
}

@Getter
@Setter
class Barracuda extends Entity implements Biteable {
    private int biteForce;
    public Barracuda(int lifePoints, int biteForce) {
        super(lifePoints);
        this.biteForce = biteForce;
    }

    @Override
    public int getBiteForce() {
        return biteForce;
    }
}

class Human extends Entity implements NonBiteable {
    public Human(int lifePoints) {
        super(lifePoints);
    }
}


public class HonestInterview {
    public static void main(String[] args) {
        Shark shark = new Shark(14, 7);
        Barracuda barracuda = new Barracuda(6, 4);
        Human human = new Human(10);
        shark.bite(shark);
        System.out.println("Is Shark alive : " + shark.isAlive());
        barracuda.bite(shark);
        System.out.println("Is Shark alive : " + shark.isAlive());
        barracuda.bite(shark);
        System.out.println("Is Shark alive : " + shark.isAlive());
    }
}
