import { Component, OnInit } from '@angular/core';
import { Categoria } from '../../models/categoria.model';
import { CategoriaService } from '../../services/categoria.service';

@Component({
    selector: 'app-categoria-list',
    templateUrl: './categoria-list.component.html'
})
export class CategoriaListComponent implements OnInit {

    categorias: Categoria[] = [];

    constructor(private service: CategoriaService) {}

    ngOnInit(): void {
        this.listar();
    }

    listar(): void {
        this.service.listar().subscribe(res => {
            this.categorias = res;
        });
    }

    deletar(id: number): void {
        this.service.deletar(id).subscribe(() => {
            this.listar();
        });
    }
}