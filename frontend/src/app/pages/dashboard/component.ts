dados: any = {};

ngOnInit(): void {

    this.http
        .get('http://localhost:8080/dashboard')
        .subscribe(res => {

            this.dados = res;

        });
}