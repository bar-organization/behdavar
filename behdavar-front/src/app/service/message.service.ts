import {Injectable} from "@angular/core";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({providedIn: 'root'})
export class MessageService {

  constructor(private _snackBar: MatSnackBar) {
  }

  public showGeneralError(errorMessage: string,error?: any) {

    this._snackBar.open(`${errorMessage} ${error ? '[ ' + error + ']' : ''} `, 'X', {
      duration: 5000, panelClass: ['bg-danger', 'text-white']
    });
  }

  public showGeneralSuccess(successMessage: string, value?: any) {
    this._snackBar.open(successMessage, 'X', {
      duration: 5000, panelClass: ['bg-success', 'text-white']
    });
  }
}
