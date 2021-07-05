Date.prototype.today = function () { 
    return this.getDate() +"/"+ (this.getMonth()+1) +"/"+ this.getFullYear();
}

Date.prototype.zerotoday = function () { 
    return ((this.getDate() < 10)?"0":"") + this.getDate() +"/"+(((this.getMonth()+1) < 10)?"0":"") + (this.getMonth()+1) +"/"+ this.getFullYear();
}

window['myGoddamnDate'] = function() {
  return new Date().today()
}
