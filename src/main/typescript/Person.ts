class Prnz {
    constructor(public name: string) {}
}

class Human extends Prnz {
    constructor(name: string, public age: number) {
        super(name);
    }
}

class Man extends Human {
    constructor(name: string, age: number, public job: string) {
        super(name, age);
    }
}
