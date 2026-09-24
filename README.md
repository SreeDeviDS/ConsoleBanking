# Console Banking System (Core Java)

A menu-driven banking application in Core Java. It manages Savings and Current accounts entirely in the console and was built to practise object-oriented design and the Java Collections framework.

## Features

- Create a Savings or Current account with an opening balance (a negative opening balance is set to 0)
- Select an account by its ID and work with it
- Deposit money and withdraw money, with a clear success or failure message for each
- Check the available balance
- View the transaction history of an account, including failed attempts (invalid amount, insufficient balance, overdraft limit exceeded)
- Apply interest to Savings accounts (4% of the current balance)
- Input validation: text or empty input at any prompt is rejected and the user is asked again, so the program does not crash

## Account rules

| | Savings Account | Current Account |
|---|---|---|
| Withdrawal | Rejected if the amount is more than the balance | Allowed below zero up to an overdraft limit of 5000 |
| Interest | 4% of the current balance | Not applicable |
| Invalid deposit or withdrawal amount (0 or negative) | Rejected and logged in the history | Rejected and logged in the history |

The interest rate and the overdraft limit are constants in `SavingsAccount` and `CurrentAccount`.

## OOP concepts used

- **Abstraction:** `Account` is an abstract class with an abstract `withdraw()` method.
- **Inheritance:** `SavingsAccount` and `CurrentAccount` extend `Account`.
- **Interface:** `InterestApplicable` declares `applyInterest()` and is implemented only by `SavingsAccount`.
- **Polymorphism:** `withdraw()` behaves differently in each subclass. The menu works with `Account` references and uses `instanceof InterestApplicable` to decide whether interest applies.
- **Encapsulation:** balance and account ID are private and accessed through getters and setters.
- **Collections:** `ArrayList<Account>` holds all accounts, and each account keeps its own `ArrayList<String>` of transaction history.
- **Static counter:** a static field generates a unique account ID for each new account.

## Project structure

```
ConsoleBanking/
└── src/
    ├── Account.java            (abstract base class)
    ├── SavingsAccount.java     (extends Account, implements InterestApplicable)
    ├── CurrentAccount.java     (extends Account, overdraft limit)
    ├── InterestApplicable.java (interface)
    └── Main.java               (menu, input handling and program entry point)
```

## How to run

Requires JDK 8 or later. From the project root:

```bash
javac -d out src/*.java
java -cp out Main
```

## Example session

Menu:

```
--- MENU ---
1. Create Account
2. Select Account
3. Deposit
4. Withdraw
5. View History
6. Check Balance
7. Apply interest
8. Exit
```

Creating a Savings account with 10000, depositing 2000, withdrawing 3000 and applying interest gives this history:

```
Transaction history:
Account created with balance: 10000.0 and AccountID: 1
Deposited: 2000.0, Balance: 12000.0
Withdrawn: 3000.0, Balance: 9000.0
Interest applied: 360.0, Balance: 9360.0
```

## Limitations and planned improvements

- Data is stored in memory only and is lost when the program exits. Planned: persist accounts and transactions in MySQL using JDBC.
- Balances use `double`. `BigDecimal` is the correct type for money.
- Add JUnit tests for deposit, withdraw, interest and the overdraft limit.
- Move the code into packages and use custom exceptions instead of history messages for failed operations.
- Next step: rebuild it as a Spring Boot REST API with accounts and transactions in MySQL.
