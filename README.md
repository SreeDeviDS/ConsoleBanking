# Banking Management System (Core Java)

A console-based banking application written in Core Java. It lets you create Savings and Current accounts and perform deposits, withdrawals, balance checks, interest calculation, and transaction history from a simple menu. The project focuses on object-oriented design: abstraction, inheritance, interfaces, and polymorphism.

## Features

- Create a **Savings** or **Current** account with an opening balance
- Select an existing account by its ID
- Deposit and withdraw money, with validation for invalid amounts
- Check the available balance
- View the transaction history for an account
- Apply interest to Savings accounts
- Input validation on the menu, so wrong choices don't crash the program

## Object-oriented design

| Concept | Where it is used |
|---|---|
| **Abstraction** | `Account` is an abstract class with an abstract `withdraw()` method |
| **Inheritance** | `SavingsAccount` and `CurrentAccount` extend `Account` |
| **Interface** | `InterestApplicable` is implemented only by `SavingsAccount` |
| **Polymorphism** | Each account type has its own `withdraw()` behaviour, called through the `Account` type |
| **Encapsulation** | Balance and account ID are private, with getters and setters |
| **Collections** | `ArrayList` stores accounts and each account's transaction history |

## Business rules

- A negative opening balance is set to 0.
- **Savings account:** a withdrawal is rejected if it is more than the balance. Interest is applied at a sample rate of 4%.
- **Current account:** withdrawals are allowed even when the balance goes below zero (no overdraft limit in this simple version).
- Deposits and withdrawals of zero or negative amounts are rejected and recorded in the history.

## Project structure

```
src/
├── Main.java               Console menu (class Tech)
├── Account.java            Abstract base class
├── SavingsAccount.java     Savings account, implements InterestApplicable
├── CurrentAccount.java     Current account
└── InterestApplicable.java Interface for accounts that earn interest
```

## How to run

You need JDK 8 or later.

```
javac src/*.java
java -cp src Tech
```

## Menu

```
1. Create Account
2. Select Account
3. Deposit
4. Withdraw
5. View History
6. Check Balance
7. Apply interest
8. Exit
```

## Known limitations

- Money is stored as `double`. A real banking system would use `BigDecimal` to avoid rounding errors.
- Data is kept in memory only, so accounts are lost when the program exits.
- Account IDs come from a simple static counter.
- No automated tests yet.

## Possible improvements

- Use `BigDecimal` for all amounts
- Save accounts to a file or a MySQL database
- Add custom exceptions for insufficient funds and invalid input
- Add JUnit tests
- Add an overdraft limit for Current accounts

## Author

SreeDevi DS · [LinkedIn](https://www.linkedin.com/in/sreedevi-ds-50914a177)
