class AutoIndent:
    """
    果物の各属性値やヘルパー関数を保持する。

    Attributes
    ----------
    braces : int
        {}の入れ子の数
    """

    def __init__(self,it,poa):
        self.braces=0
        self.indention_target=it
        self.previous_or_ahead=poa



    def Indent(self, str):
        """
        インデントをつける。

        Parameters
        ----------
        ｓｔｒ　: Str
            インデント設定対象の文字列。

        Returns
        -------
        fruit_price : Str
            インデント設定後の文字列。
        """
        while True:
            if str[0:1] == "\t":
                str = str[1:]
            elif str[0:1] == " ":
                str = str[1:]
            else:
                break
        i = 0
        while i < self.braces:
            str = "\t" + str


    def BracesGroup(self, str):
        """
        {}で文字列を分ける

        Parameters
        ----------
        ｓｔｒ　: Str
            切り分け前の文字列

        Returns
        -------
        fruit_price : Str[]
            グループごとの文字列リスト。
        """


    def BracesFinish(self):
        """
            {}で文字列を分ける

        Parameters
        ----------
        ｓｔｒ　: Str
            切り分け前の文字列

        Returns
        -------
        fruit_price : Str[]
            グループごとの文字列リスト。
        """
        pass



if __name__=='__main__':
