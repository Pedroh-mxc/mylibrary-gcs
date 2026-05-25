import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoriaService } from '../../services/categoria.service';

@Component({
    selector: 'app-categoria-form',
    templateUrl: './categoria-form.component.html'
})
export class CategoriaFormComponent {

    form = this.fb.group({
        nome: ['', Validators.required],
        descricao: ['']
    });

    constructor(
        private fb: FormBuilder,
        private service: CategoriaService,
        private router: Router
    ) {}

    salvar(): void {

        if (this.form.invalid) {
            return;
        }

        this.service
            .salvar(this.form.value as any)
            .subscribe(() => {
                this.router.navigate(['/categorias']);
            });
    }
}