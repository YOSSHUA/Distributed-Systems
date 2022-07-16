package mx.itam.packages.jmstopicsfinancialsystem;

public class Deployer {
    public static void main(String[] args){
        FloorBroker brokers[];
        String subjects[] = {"Telecommunications", "Banks", "Transportation", "FoodSupply", "Education"};
        int n_brokers = (int)(Math.random()*11);
        try{
            System.out.println("Creating "+ n_brokers + " brokers");
            brokers = new FloorBroker[n_brokers];
            for(int i = 0; i < n_brokers; i++){
                String topic = subjects[(int)(Math.random()*5)];
                brokers[i] = new FloorBroker(topic, i);
                brokers[i].start();
            }
            new InformationProvider().produceMessages();
            for(int i = 0; i < n_brokers; i++)
                brokers[i].join();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
