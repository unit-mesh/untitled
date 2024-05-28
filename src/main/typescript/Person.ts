class Orz {
    constructor(public name: string) {
    }
}

class Human extends Orz {
    constructor(name: string, public age: number) {
        super(name);
    }
}

class Man extends Human {
    constructor(name: string, age: number, public job: string) {
        super(name, age);
    }

    ageGet() {
        return this.ageGet
    }

    format() {
        return this.ageGet()
    }
}
