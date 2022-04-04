package learn.bank;

import java.util.*;

public class BankService {
    /**
     * Поле класса содержащее в себе пользователя и его счета
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет нового пользователя банка
     * @param user пользователь, которого добавят в систему
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод проверяет, что по такому паспорту в банке есть пользователь, а затем добавляет
     * ему новый счет в список его счетов
     * @param passport паспорт пользователя, по которому происходит поиск
     * @param account аккаунт, который добавится к списку
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accList = users.get(user);
            if (!accList.contains(account)) {
                accList.add(account);
            }
        }
    }

    /**
     * Метод ищет, что в поле объекта есть пользователь с таким паспортом
     * @param passport по которому пользователь ищется в поле
     * @return если введен верно, вернет найденного пользователя
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод проверяет, что по паспорту есть пользователь, а затем ищет по реквизитам нужный аккаунт
     * среди списка аккаунтов пользователя
     * @param passport принимает паспорт, по которому ищет список аккаунтов
     * @param requisite по которым ищется нужный аккаунт
     * @return если данные верны, метод вернет нужный аккаунт
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод проверяет, что по входным данным существуют счета, а затем совершает перевод с одного счета на другой.
     * @param srcPassport паспорт отправителя, по которым ищется аккаунт
     * @param srcRequisite реквизиты отправителя, по которым ищется счет
     * @param destPassport паспорт получателя, по которым ищется аккаунт
     * @param destRequisite реквизиты получателя, по которым ищется счет
     * @param amount сумма перевода
     * @return возвращает значение, в зависимости от того, был совершен перевод или нет
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite,
                                 double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount == null || srcAccount.getBalance() < amount
                || destAccount == null) {
            return false;
        }
        srcAccount.setBalance(srcAccount.getBalance() - amount);
        destAccount.setBalance(destAccount.getBalance() + amount);
        return true;
    }
}
